import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { delay} from 'rxjs/operators';
import * as jwt_decode from 'jwt-decode';
import * as moment from 'moment';

import { environment } from '../../../environments/environment';
import { of, EMPTY } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable, map, throwError, catchError } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {
    private _headers: HttpHeaders | { [header: string]: string | string[]; } | undefined;

    constructor(private http: HttpClient,
        @Inject('LOCALSTORAGE') private localStorage: Storage,
        private router: Router
        ) {
    }

    login(email: string, password: string) {
        return of(true)
            .pipe(delay(1000),
                map((/*response*/) => {
                    // set token property
                    // const decodedToken = jwt_decode(response['token']);

                    // store email and jwt token in local storage to keep user logged in between page refreshes
                    this.localStorage.setItem('currentUser', JSON.stringify({
                        token: 'aisdnaksjdn,axmnczm',
                        isAdmin: true,
                        email: 'john.doe@gmail.com',
                        id: '12312323232',
                        alias: 'john.doe@gmail.com'.split('@')[0],
                        expiration: moment().add(1, 'days').toDate(),
                        fullName: 'John Doe'
                    }));

                    return true;
                }));
    }

    logout(): void {
        // clear token remove user from local storage to log user out
        this.localStorage.removeItem('currentUser');
    }

    getCurrentUser(): any {
        // TODO: Enable after implementation
        // return JSON.parse(this.localStorage.getItem('currentUser'));
        return {
            token: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTIwNDQ1NTksInVzZXJfbmFtZSI6ImRheXZzb24ucmVkQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjpbIlJPTEVfT1BFUkFUT1IiXSwianRpIjoiNGJkZTZiM2MtNDRmZC00NDAyLWE0NTItN2UyNGM3OTI3NjFkIiwiY2xpZW50X2lkIjoiQV',
            isAdmin: true,
            email: 'dayvson.red@gmail.com',
            id: '3459e687-b5a8-453a-a764-0e5be08ca400',
            alias: 'dayvson.red@gmail.com'.split('@')[0],
            expiration: moment().add(1, 'days').toDate(),
            fullName: 'Dayvison'
        };
    }

    passwordResetRequest(email: string) {
        return of(true).pipe(delay(1000));
    }

    changePassword(email: string, currentPwd: string, newPwd: string) {
        return of(true).pipe(delay(1000));
    }

    passwordReset(email: string, token: string, password: string, confirmPassword: string): any {
        return of(true).pipe(delay(1000));
    }

    
	public isAuthenticated(): boolean {
		const token = localStorage.getItem('access_token');

		if (!token) return false;

		const jwtHelper = new JwtHelperService();
		return !jwtHelper.isTokenExpired(token);
	}

    public sign(payload: { email: string; password: string }): Observable<any> {
		console.log("1 send login to service");
		this._headers = {
			'Authorization': "Basic QVBJX05BTUVfQUNDRVNTOkFQSV9TRUNSRVRfQUNDRVNT",
			"Content-Type": "application/x-www-form-urlencoded",
		};
		const Params = new URLSearchParams();
		Params.set('grant_type', 'password');
		Params.set('username', payload.email);
		Params.set('password', payload.password);
        console.log(" 2 send login to service");


        let options = {
            headers: new HttpHeaders()
            .set('Content-Type', 'application/x-www-form-urlencoded')
            .set('Authorization', "Basic QVBJX05BTUVfQUNDRVNTOkFQSV9TRUNSRVRfQUNDRVNT")
        };


		return this.http.post<any>(`${environment.urlBase}${environment.authorization}`, Params.toString(), options ).pipe(
			map((res) => {
                console.log(res);
				localStorage.removeItem('access_token');
				localStorage.removeItem('token');
				localStorage.setItem('access_token', res.access_token);
				localStorage.setItem('token', res.access_token);

                this.localStorage.setItem('currentUser', JSON.stringify({
                    token: res.access_token,
                    isAdmin: false,
                    email: 'dayvson.red@gmail.com',
                    id: '3459e687-b5a8-453a-a764-0e5be08ca400',
                    alias: 'dayvson.red@gmail.com'.split('@')[0],
                    expiration: moment().add(1, 'days').toDate(),
                    fullName: 'Dayvison'
                }));

				//return this.router.navigate(['dashboard']);
                return true
			}),
			catchError((e) => {
				if (e.error.message) return throwError(() => e.error.message);

				return throwError(
					() =>
						'No momento não estamos conseguindo validar este dados, tente novamente mais tarde!'
				);
			})
		);
	}

    
	public signerror(payload: { email: string; password: string }){
		console.log("send login to service")
		const headersOld = {
			'Authorization': "Basic QVBJX05BTUVfQUNDRVNTOkFQSV9TRUNSRVRfQUNDRVNT",
			"Content-Type": "application/x-www-form-urlencoded",
		};
        /*
        const headers: HttpHeaders({ 
            'Authorization': "Basic QVBJX05BTUVfQUNDRVNTOkFQSV9TRUNSRVRfQUNDRVNT",
            'Content-Type': "application/x-www-form-urlencoded" })
            */

        /*const requestOptions = {                                                                                                                                                                                 
            headers: new HttpHeaders(headersSend), 
          };*/

          const iheaders = new HttpHeaders();
          iheaders.append('Content-Type', 'application/x-www-form-urlencoded')
          iheaders.append('Authorization', "Basic QVBJX05BTUVfQUNDRVNTOkFQSV9TRUNSRVRfQUNDRVNT")

            /*
        let options = {
            headers: new HttpHeaders({ 
                'Authorization': "Basic QVBJX05BTUVfQUNDRVNTOkFQSV9TRUNSRVRfQUNDRVNT",
                'Content-Type': "application/x-www-form-urlencoded" })
        }  */

		const Params = new URLSearchParams();
		Params.set('grant_type', 'password');
		Params.set('username', payload.email);
		Params.set('password', payload.password);

        let options = {
            headers: new HttpHeaders()
            .set('Content-Type', 'application/x-www-form-urlencoded')
            .set('Authorization', 'Basic QVBJX05BTUVfQUNDRVNTOkFQSV9TRUNSRVRfQUNDRVNT')
        };

        const headers = {
			'Authorization': "Basic QVBJX05BTUVfQUNDRVNTOkFQSV9TRUNSRVRfQUNDRVNT",
			"Content-Type": "application/x-www-form-urlencoded",
		};

        this.http.post<{
            access_token: string, 
            expires_in: string, 
            jti: string, 
            token_type: string 
           }>(`${environment.urlBase}${environment.authorization}`, Params.toString(), {headers}).subscribe(
              (res) => {
                console.log(res)
				// localStorage.removeItem('access_token');
				// localStorage.removeItem('token');
				// localStorage.setItem('access_token', res.access_token);
				// localStorage.setItem('token', res.access_token);


                // this.localStorage.setItem('currentUser', JSON.stringify({
                //     token: res.access_token,
                //     isAdmin: true,
                //     email: payload.email,
                //     id: '12312323232',
                //     alias: payload.email.split('@')[0],
                //     expiration: moment().add(1, 'days').toDate(),
                //     fullName: 'Dayvson'
                // }));

				//return this.router.navigate(['dashboard']);
                return true;
              }
            //   () => {
            //     return 'No momento não estamos conseguindo validar este dados, tente novamente mais tarde!';
            //   }
            
          );

		// return this.http.post<{
        //      access_token: string, 
        //      expires_in: string, 
        //      jti: string, 
        //      token_type: string 
        //     }>(`${environment.urlBase}${environment.authorization}`, Params.toString(), options ).pipe( map((res) => {
        //         console.log(res)
		// 		// localStorage.removeItem('access_token');
		// 		// localStorage.removeItem('token');
		// 		// localStorage.setItem('access_token', res.access_token);
		// 		// localStorage.setItem('token', res.access_token);


        //         // this.localStorage.setItem('currentUser', JSON.stringify({
        //         //     token: res.access_token,
        //         //     isAdmin: true,
        //         //     email: payload.email,
        //         //     id: '12312323232',
        //         //     alias: payload.email.split('@')[0],
        //         //     expiration: moment().add(1, 'days').toDate(),
        //         //     fullName: 'Dayvson'
        //         // }));

		// 		//return this.router.navigate(['dashboard']);
        //         return true;
		// 	}),
		// 	catchError((e) => {
		// 		if (e.error.message) return throwError(() => e.error.message);

		// 		return throwError(
		// 			() =>
		// 				'No momento não estamos conseguindo validar este dados, tente novamente mais tarde!'
		// 		);
		// 	})
		// );
	}

    // this.http.post<{
    //     access_token: string, 
    //     expires_in: string, 
    //     jti: string, 
    //     token_type: string 
    //    }>(`${environment.urlBase}${environment.authorization}`, Params.toString(),  {
    //        headers: new HttpHeaders()
    //          .set('Content-Type', 'application/x-www-form-urlencoded')
    //          .set('Authorization', "Basic QVBJX05BTUVfQUNDRVNTOkFQSV9TRUNSRVRfQUNDRVNT")
    //      }).pipe( map((res) => {
    
	public createNewUserLongin(payload : {
		email : string, 
		name: string, 
		password: string,
		cpf: string,
		offshoot: string,
		longitude: string,
		latitude: string
	}): Observable<any> {
		console.log("start creat login of user");
		console.log(payload);
		const body = payload; 

		const headers = {
			'Content-Type': "application/json",
		};

		return this.http.post<any>(`${environment.urlBase}${environment.link_creat_login}`, body, { headers }).pipe(
			map((res) => {
				console.log("res creat login of user");
				console.log(res);

				//return this.router.navigate(['new/user/valid/email']);	
				return null;
			}),
			catchError((e) => {
				if (e.error.message) return throwError(() => e.error.message);
				return throwError(
					() =>
						'No momento não estamos conseguindo validar este dados, tente novamente mais tarde!'
				);
			})
		);
	}

	public validEmailUser(payload : {
		email : any, 
		user: any, 
	}): Observable<any> {
		console.log("valid user");
		console.log(payload);
		const body = payload; 

		const headers = {
			'Content-Type': "application/json",
		};

		return this.http.get<any>(`${environment.urlBase}${environment.link_creat_valid_email}/${payload.email}/${payload.user}`, { headers }).pipe(
			map((res) => {
				console.log("res valid user");
				console.log(res);

				//return this.router.navigate(['new/user/valid/email']);	
				return null;
			}),
			catchError((e) => {
				if (e.error.message) return throwError(() => e.error.message);
				return throwError(
					() =>
						'No momento não estamos conseguindo validar este dados, tente novamente mais tarde!'
				);
			})
		);
	}
}
