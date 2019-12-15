import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Store } from 'app/shared/model/store.model';
import { StoreService } from 'app/entities/store/store.service';

@Component({
  selector: 'jhi-store-view',
  templateUrl: './store-view.component.html'
})
export class StoreViewComponent implements OnInit, OnDestroy {
  @Input() store: Store;
  @Input() currentAccount: any;
  likedStore = false;

  constructor(private storeService: StoreService) {}

  ngOnDestroy(): void {}

  ngOnInit(): void {
    for (let i = 0; i < this.store.users.length; i++) {
      if (this.store.users[i].login === this.currentAccount.login) {
        this.likedStore = true;
      }
    }
  }

  addStoreToFav() {
    this.store.users.push(this.currentAccount);
    this.storeService.update(this.store).subscribe();
    this.likedStore = true;
  }

  removeStoreFromFav() {
    for (let i = 0; i < this.store.users.length; i++) {
      if (this.store.users[i].login === this.currentAccount.login) {
        this.store.users.splice(i, 1);
        i--;
      }
    }
    this.storeService.update(this.store).subscribe();
    this.likedStore = false;
  }
}
