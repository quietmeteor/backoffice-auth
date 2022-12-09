import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['./group-list.component.css']
})
export class GroupListComponent implements OnInit {

  actRoute: ActivatedRoute;
  router: Router;

  constructor(actRoute: ActivatedRoute, router: Router) {
    this.actRoute = actRoute;
    this.router = router;
  }

  ngOnInit(): void {

  }

  createGroup() {
    this.router.navigateByUrl("/api/group-create");
  }


  detail(id: Number) {
    this.router.navigateByUrl("/api/group-detail/" + id);
  }

  edit(id: Number) {
    this.router.navigateByUrl("/api/group-edit/" + id);

  }

  deleteGroup() {

  }

}
