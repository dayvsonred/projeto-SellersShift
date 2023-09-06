import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SalesRoutingModule } from './sales-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { SalesListComponent } from './sales-list/sales-list.component';

@NgModule({
    imports: [
        CommonModule,
        SalesRoutingModule,
        SharedModule
    ],
    declarations: [
        SalesListComponent
    ]
})
export class SalesModule { }
