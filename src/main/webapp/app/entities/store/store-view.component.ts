import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { IStore, Store } from 'app/shared/model/store.model';
import { StoreService } from 'app/entities/store/store.service';

@Component({
  selector: 'jhi-store-view',
  templateUrl: './store-view.component.html'
})
export class StoreViewComponent implements OnInit, OnDestroy {
  @Input() store: Store;
  @Input() currentAccount: any;
  @Output() selectedStore: EventEmitter<IStore> = new EventEmitter<IStore>();
  likedStore = false;

  constructor(private storeService: StoreService) {}

  ngOnDestroy(): void {}

  ngOnInit(): void {}

  addStoreToFav() {
    this.store.users.push(this.currentAccount);
    this.storeService.update(this.store).subscribe();
    this.likedStore = true;
    this.selectedStore.emit(this.store);
  }
}
