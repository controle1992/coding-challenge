import { Component, OnInit } from '@angular/core';
import { IStore } from 'app/shared/model/store.model';
import { StoreService } from 'app/entities/store/store.service';
import { AccountService } from 'app/core/auth/account.service';

@Component({
  selector: 'jhi-store-favorite',
  templateUrl: './store-favorite.component.html'
})
export class StoreFavoriteComponent implements OnInit {
  favoriteStores: IStore[];
  currentAccount: any;

  constructor(private storeService: StoreService, private accountService: AccountService) {}

  ngOnInit(): void {
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.loadAll();
  }

  loadAll() {
    this.storeService.findFavorites().subscribe(response => {
      this.favoriteStores = response.body;
    });
  }

  trackId(index: number, item: IStore) {
    return item.id;
  }

  removeStoreFromFav(store: IStore) {
    for (let i = 0; i < store.users.length; i++) {
      if (store.users[i].login === this.currentAccount.login) {
        store.users.splice(i, 1);
        i--;
      }
    }
    this.storeService.update(store).subscribe(() => this.loadAll());
  }
}
