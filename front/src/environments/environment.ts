import { NgxLoggerLevel } from 'ngx-logger';

// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  production_mock: true,
  logLevel: NgxLoggerLevel.TRACE,
  serverLogLevel: NgxLoggerLevel.OFF,
  authorization: `/oauth/oauth/token`,
	link_creat_login: `/core/login`,
	link_creat_valid_email: `/core/valid/email`,
	urlBase: `http://localhost:8765`,
  link_product_get: `/product/product/test`,

};
