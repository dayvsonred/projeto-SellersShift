import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardHomeComponent } from './dashboard-home/dashboard-home.component';
import { SharedModule } from 'src/app/shared/shared.module';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { DashboardProductComponent } from './dashboard-product/dashboard-product.component';

import {Component, ViewChild} from '@angular/core';
import {MatAccordion, MatExpansionModule} from '@angular/material/expansion';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatNativeDateModule} from '@angular/material/core';
import { DashboardBuyComponent } from './dashboard-buy/dashboard-buy.component';
import { DashboardBuyFinalityComponent } from './dashboard-buy-finality/dashboard-buy-finality.component';

@NgModule({
    declarations: [DashboardHomeComponent, DashboardProductComponent, DashboardBuyComponent, DashboardBuyFinalityComponent],
    imports: [
        CommonModule,
        DashboardRoutingModule,
        SharedModule,
        MatGridListModule,
        MatButtonModule,
        MatCardModule,
        MatButtonModule,
        MatExpansionModule,
        MatIconModule,
        MatFormFieldModule,
        MatInputModule,
        MatDatepickerModule,
        MatNativeDateModule,
        
    ]
})
export class DashboardModule { }
