import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { Login } from '../login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: Login = new Login();
  loginData: any;
  constructor(private router:Router, private employeeService:EmployeeService) { }

  ngOnInit(): void {
  }

  onSubmit(){
    this.employeeService.login(this.login).subscribe(data =>{
      debugger;
    this.loginData = data;
     localStorage.setItem('role',this.loginData.role)
     alert(this.loginData.role)
      this.router.navigate(['/employees']);
    },
    error=> alert(JSON.stringify(error.error.message)))
  }
}
