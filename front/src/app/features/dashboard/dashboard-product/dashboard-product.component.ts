import { Component, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/core/services/notification.service';
import { Title } from '@angular/platform-browser';
import { NGXLogger } from 'ngx-logger';
import { AuthenticationService } from 'src/app/core/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard-product',
  templateUrl: './dashboard-product.component.html',
  styleUrls: ['./dashboard-product.component.css']
})
export class DashboardProductComponent implements OnInit {
  currentUser: any;
  produtos: any;
  produto: any;

  constructor(private notificationService: NotificationService,
    private authService: AuthenticationService,
    private titleService: Title,
    private router: Router,
    // private logger: NGXLogger
    ) {
  }

  ngOnInit() {
    let array = [];
    let  pd = {
      name : "aaaaa",
      img : "https://m.media-amazon.com/images/I/51ftyQ4Q54L._AC_SX522_.jpg"
    };
    this.produto = pd;
    let  pd2 = {
      name : "bbbbb",
      img : "https://m.media-amazon.com/images/I/51cf2LQtBXL.__AC_SX300_SY300_QL70_ML2_.jpg"
    };
    array.push(pd);array.push(pd);array.push(pd);array.push(pd);array.push(pd2);array.push(pd2);array.push(pd2);array.push(pd2);array.push(pd2);array.push(pd2);
    console.log(array);
    this.produtos = array;
    this.currentUser = this.authService.getCurrentUser();
    this.titleService.setTitle('SellersShift - Dashboard');
    // this.logger.log('Dashboard loaded');


  }

  viewProduct(produtct: any){
    console.log("view produtct");
    console.log(produtct);
  }

  goToBuy(){
    this.router.navigate(['dashboard/buy']);
  }


}
