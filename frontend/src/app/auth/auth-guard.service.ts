import { RouterService } from './../router/router.service'
import { Injectable } from '@angular/core'
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router'

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {
  constructor (private router: RouterService) { }

  canActivate (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const token = localStorage.getItem('token')
    const role = localStorage.getItem('role')
    if (token) {
      if (route.data.role && route.data.role.indexOf(role) === -1) {
        this.router.toHomeScreen()
        return false
      }
      return true
    }
    this.router.toLoginScreen()
    return false
  }
}
