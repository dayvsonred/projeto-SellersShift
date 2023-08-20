import { NgxLoggerLevel } from 'ngx-logger';

export const environment = {
  production: true,
  logLevel: NgxLoggerLevel.OFF,
  serverLogLevel: NgxLoggerLevel.ERROR,
  authorization: `/oauth/oauth/token`,
	link_creat_login: `/core/login`,
	link_creat_valid_email: `/core/valid/email`,
	urlBase: `http://localhost:8765`,
};
