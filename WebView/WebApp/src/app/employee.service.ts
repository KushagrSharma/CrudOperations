import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Observer } from 'rxjs';
import {Employee} from './employee';
import { Login } from './login';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = "http://localhost:8080/api/v1/employees"
  private loginUrl= "http://localhost:8080/api/v1/user/login"
  constructor(private httpClient: HttpClient ) { }

  getEmployeeList() : Observable<Employee[]>{
    return this.httpClient.get<Employee[]>(`${this.baseUrl}`);
  }

  createEmployee(employee: Employee):Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}`,employee);
  }

  getEmployeeByID(id:number): Observable<Employee>{
   return this.httpClient.get<Employee>(`${this.baseUrl}/${id}`);
  }

  updateEmployee(id:number, employee:Employee): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/${id}`, employee)
  }

  deleteEmployee(id:number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }

  login(login: Login):Observable<Object>{
    return this.httpClient.post(`${this.loginUrl}`,login);
  }
}
