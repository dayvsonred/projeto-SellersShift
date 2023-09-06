import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ShoppingRoutingModule } from './shopping-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';

@NgModule({
    imports: [
        CommonModule,
        ShoppingRoutingModule,
        SharedModule
    ],
    declarations: [
        ShoppingListComponent
    ]
})
export class ShoppingModule { }
