import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AvValidEmailRoutingModule } from './av-valid-email-routing.module';
import { AvValidEmailComponent } from './av-valid-email.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';

import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarModule,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import {MatSelectModule} from '@angular/material/select';

import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatDividerModule} from '@angular/material/divider';


@NgModule({
  imports: [
    FormsModule,
    AvValidEmailRoutingModule,
    ReactiveFormsModule, 
    // BrowserModule,
    CommonModule,
    //HttpClientModule, 
    MatToolbarModule,MatCardModule,MatButtonModule,MatTableModule,MatFormFieldModule,MatInputModule,
    MatSelectModule,
    MatSnackBarModule,
    MatProgressBarModule,
    MatDividerModule,
    
  ],
  declarations: [AvValidEmailComponent]
})
export class AvValidEmailModule { }
