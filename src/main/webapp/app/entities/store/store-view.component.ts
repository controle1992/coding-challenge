import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Store } from 'app/shared/model/store.model';

@Component({
  selector: 'jhi-store-view',
  templateUrl: './store-view.component.html'
})
export class StoreViewComponent implements OnInit, OnDestroy {
  @Input() store: Store;

  ngOnDestroy(): void {}

  ngOnInit(): void {}
}
