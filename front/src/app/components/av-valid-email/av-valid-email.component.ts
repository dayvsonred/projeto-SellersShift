import { Input, Component, Output, EventEmitter, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormControl, FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AuthService } from '../services/auth.service';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './av-valid-email.component.html',
  styleUrls: ['./av-valid-email.component.scss'],
})
export class AvValidEmailComponent implements OnInit {

  form: FormGroup | any;
  private formSubmitAttempt: boolean | undefined;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  loading = false;
  submitted = false;
  returnUrl: string | undefined;
  error = '';

  constructor(private router: Router,
    private fb: FormBuilder,
    private authService: AuthService,
    private _snackBar: MatSnackBar
  ) { }

  ngOnInit() {
    this.form = this.fb.group({
      userName: ['', Validators.required],
      userEmail: ['', Validators.required],
      userLatitude: ['', Validators.required],
      userLogitude: ['', Validators.required],
      userOffshoot: ['', Validators.required],
      userCpf: ['', Validators.required],
      password: ['', Validators.required],
      passwordConfirm: ['', Validators.required],
    });
  }

  loginUser() {
    //execute your post rquest and set token in local storage or session storage or cokie storage
    //The token may be aby string. here i'm use my_token as the token.
    localStorage.setItem('token', 'my_token');

    this.router.navigate(['dashboard']);
  }

  openSnackBar() {
    this._snackBar.open('the passwords are different!!', 'Ok', {
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  goLogin(){
    this.router.navigate(['']);
  }

}
