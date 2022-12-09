import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent {

  router: Router;
  actRoute: ActivatedRoute;
	
  constructor(actRoute: ActivatedRoute, router: Router) {
    this.router = router;
    this.actRoute = actRoute
  }



  createUser() {
    this.router.navigateByUrl("/api/user-create");

  }

  detail(id: Number) {
    this.router.navigateByUrl("/api/user-detail/" + id);
  }

  deleteUser() {

  }

  edit(id:Number) {
    this.router.navigateByUrl("/api/user-edit/"+id)
  }


}
