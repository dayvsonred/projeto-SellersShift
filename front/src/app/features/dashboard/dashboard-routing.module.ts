import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from 'src/app/shared/layout/layout.component';

import { DashboardHomeComponent } from './dashboard-home/dashboard-home.component';
import { DashboardProductComponent } from './dashboard-product/dashboard-product.component';
import { DashboardBuyComponent } from './dashboard-buy/dashboard-buy.component';
import { DashboardBuyFinalityComponent } from './dashboard-buy-finality/dashboard-buy-finality.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', component: DashboardHomeComponent },
      { path: 'produto/:id', component: DashboardProductComponent },
      { path: 'produto', component: DashboardProductComponent },
      { path: 'buy', component: DashboardBuyComponent },
      { path: 'buy-finally', component: DashboardBuyFinalityComponent },
      { path: 'buy-finally/:id', component: DashboardBuyFinalityComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
