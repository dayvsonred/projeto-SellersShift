import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductsRoutingModule } from './products-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { ProductsListComponent } from './products-list/products-list.component';

@NgModule({
    imports: [
        CommonModule,
        ProductsRoutingModule,
        SharedModule
    ],
    declarations: [
        ProductsListComponent
    ],
    
})
export class ProductsModule { }
