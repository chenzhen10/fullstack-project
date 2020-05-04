import { RouterService } from './../router/router.service'
import { LoginService } from './login.service'
import { Component, OnInit } from '@angular/core'
import { FormGroup, FormControl, Validators } from '@angular/forms'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  isIncorrectCredentials:boolean = false
  formGroup:FormGroup
  constructor (private loginService:LoginService, private router:RouterService) {}

  ngOnInit (): void {
    this.initForm()
  }

  initForm () {
    this.formGroup = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
    })
  }

  login () {
    if (this.formGroup.valid) {
      this.loginService.login(this.formGroup.value).subscribe(result => {
        localStorage.setItem('token', result.token)
        localStorage.setItem('role', result.role)
        this.router.toHomeScreen()
      },
      error => {
        this.isIncorrectCredentials = true
        setTimeout(() => {
          this.isIncorrectCredentials = false
        }, 3000)
      })
    }
  }
}
