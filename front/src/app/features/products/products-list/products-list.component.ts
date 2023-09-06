import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
//import { NGXLogger } from 'ngx-logger';
import { Title } from '@angular/platform-browser';
import { NotificationService } from 'src/app/core/services/notification.service';
import { MyService } from 'src/app/core/services/my.service';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
  active : string;
  views : string;
  amount : string;
  units : string;
  sold : string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  { position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H', active: '',  views: '', amount: '', units: '', sold: '' },
  { position: 2, name: 'Helium', weight: 4.0026, symbol: 'He', active: '',  views: '', amount: '', units: '', sold: '' },
];

@Component({
  selector: 'app-customer-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {
  displayedColumns: string[] = [ 'position', 'name', 'amount', 'units', 'sold' ];
  dataSource = new MatTableDataSource(ELEMENT_DATA);

  @ViewChild(MatSort, { static: true })
  sort: MatSort = new MatSort;

  constructor(
    //private logger: NGXLogger,
    private notificationService: NotificationService,
    private titleService: Title,
    private myService: MyService
  ) { }

  ngOnInit() {
    this.titleService.setTitle('SellersShift');
    //this.logger.log('Customers loaded');
    this.notificationService.openSnackBar('Customers loaded');
    this.dataSource.sort = this.sort;

    this.getProduct();
  }

  getProduct() {
    this.myService.getProduct("").subscribe({
      next: (res) => { 

        console.log("aaaaaaaaaaaa");
        console.log(res);

       },
      error: (e) => e,
    })
  }


}
