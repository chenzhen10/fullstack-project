import { Injectable } from '@angular/core'
import { Router } from '@angular/router'

@Injectable({
  providedIn: 'root'
})
export class RouterService {
  constructor (private router: Router) { }

  toHomeScreen () {
    this.router.navigateByUrl('sensors')
  }

  toLoginScreen () {
    this.router.navigateByUrl('')
  }

  toAddScreen () {
    this.router.navigateByUrl('sensors/add')
  }

  toUpdateScreen (id) {
    this.router.navigateByUrl(`sensors/update/${id}`)
  }
}
