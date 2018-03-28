import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {catchError, map, tap} from "rxjs/operators";
import {_throw} from "rxjs/observable/throw";

import {DepartmentDto} from "../model/department-dto";

@Injectable()
export class DepartmentService {

  private baseUrl: string = 'http://localhost:8088/departments';

  constructor(private http: HttpClient) {
  }

  getDepartmentDTOs(): Observable<DepartmentDto[]> {
	return this.http.get(this.baseUrl)
  	.pipe(
    	map(this.extractData),
    	 tap(data => console.log(JSON.stringify(data))),
    	catchError(this.handleError)
  	);
  }

  private extractData(response: HttpResponse<DepartmentDto>) {
	const body = response;
	return body || {};
  }
  private handleError(err: HttpErrorResponse) {
	let errorMessage: string;

	// A client-side or network error occurred.
	if (err.error instanceof Error) {
  		errorMessage = `An error occurred: ${err.error.message}`;
	}
	// The backend returned an unsuccessful response code.
	// The response body may contain clues as to what went wrong,
	else {
  		errorMessage = `Backend returned code ${err.status}, body was: ${err.error}`;
	}
	console.error(errorMessage);
	return _throw(errorMessage);
  }
}
