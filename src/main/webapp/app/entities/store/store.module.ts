import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StoreSharedModule } from 'app/shared/shared.module';
import { StoreComponent } from './store.component';
import { StoreDetailComponent } from './store-detail.component';
import { StoreUpdateComponent } from './store-update.component';
import { StoreDeletePopupComponent, StoreDeleteDialogComponent } from './store-delete-dialog.component';
import { storeRoute, storePopupRoute } from './store.route';
import { StoreViewComponent } from 'app/entities/store/store-view.component';

const ENTITY_STATES = [...storeRoute, ...storePopupRoute];

@NgModule({
  imports: [StoreSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    StoreComponent,
    StoreDetailComponent,
    StoreUpdateComponent,
    StoreDeleteDialogComponent,
    StoreDeletePopupComponent,
    StoreViewComponent
  ],
  entryComponents: [StoreDeleteDialogComponent]
})
export class StoreStoreModule {}
