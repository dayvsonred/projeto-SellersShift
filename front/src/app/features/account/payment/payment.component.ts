import { UntypedFormGroup, UntypedFormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
//import { NGXLogger } from 'ngx-logger';
import { AuthenticationService } from 'src/app/core/services/auth.service';
import { NotificationService } from 'src/app/core/services/notification.service';
import { SpinnerService } from 'src/app/core/services/spinner.service';


@Component({
  selector: 'app-user-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  form!: UntypedFormGroup;
  hideCurrentPassword: boolean;
  hideNewPassword: boolean;
  currentPassword!: string;
  newPassword!: string;
  newPasswordConfirm!: string;
  disableSubmit!: boolean;

  numCard!: string;
  nameCard!: string;
  dateCard!: string;
  secretCard!: string;
  


  constructor(private authService: AuthenticationService,
    //private logger: NGXLogger,
    private spinnerService: SpinnerService,
    private notificationService: NotificationService) {

    this.hideCurrentPassword = true;
    this.hideNewPassword = true;
  }

  ngOnInit() {
    this.form = new UntypedFormGroup({
      currentPassword: new UntypedFormControl('', Validators.required),
      newPassword: new UntypedFormControl('', Validators.required),
      newPasswordConfirm: new UntypedFormControl('', Validators.required),

      numCard: new UntypedFormControl('', Validators.required),
      nameCard: new UntypedFormControl('', Validators.required),
      dateCard: new UntypedFormControl('', Validators.required),
      secretCard: new UntypedFormControl('', Validators.required),

    });

    this.form.get('currentPassword')?.valueChanges
      .subscribe(val => { this.currentPassword = val; });

    this.form.get('newPassword')?.valueChanges
      .subscribe(val => { this.newPassword = val; });

    this.form.get('newPasswordConfirm')?.valueChanges
      .subscribe(val => { this.newPasswordConfirm = val; });

      this.form.get('numCard')?.valueChanges
      .subscribe(val => { this.numCard = val; });
      this.form.get('nameCard')?.valueChanges
      .subscribe(val => { this.nameCard = val; });
      this.form.get('dateCard')?.valueChanges
      .subscribe(val => { this.dateCard = val; });
      this.form.get('secretCard')?.valueChanges
      .subscribe(val => { this.secretCard = val; });

    this.spinnerService.visibility.subscribe((value) => {
      this.disableSubmit = value;
    });
  }

  changePassword() {

    // if (this.newPassword !== this.newPasswordConfirm) {
    //   this.notificationService.openSnackBar('New passwords do not match.');
    //   return;
    // }

    const email = this.authService.getCurrentUser().email;

    this.authService.changePassword(email, this.currentPassword, this.newPassword)
      .subscribe(
        data => {
          //this.logger.info(`User ${email} changed password.`);
          this.form.reset();
          this.notificationService.openSnackBar('Your password has been changed.');
        },
        error => {
          this.notificationService.openSnackBar(error.error);
        }
      );
  }
}
