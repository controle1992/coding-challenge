import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IStore } from 'app/shared/model/store.model';
import { Location } from 'app/shared/model/location.model';
import { AccountService } from 'app/core/auth/account.service';
import { StoreService } from './store.service';

@Component({
  selector: 'jhi-store',
  templateUrl: './store.component.html'
})
export class StoreComponent implements OnInit, OnDestroy {
  stores: IStore[];
  currentAccount: any;
  eventSubscriber: Subscription;
  userLocation: Location = new Location();

  constructor(
    protected storeService: StoreService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.storeService
      .query(this.userLocation)
      .pipe(
        filter((res: HttpResponse<IStore[]>) => res.ok),
        map((res: HttpResponse<IStore[]>) => res.body)
      )
      .subscribe(
        (res: IStore[]) => {
          this.stores = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.getLocation();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInStores();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IStore) {
    return item.id;
  }

  registerChangeInStores() {
    this.eventSubscriber = this.eventManager.subscribe('storeListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  getLocation(): void {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(position => {
        this.userLocation.longitude = position.coords.longitude;
        this.userLocation.latitude = position.coords.latitude;
        this.loadAll();
      });
    }
    this.loadAll();
  }
}
