import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Group } from '../group';
import { GroupService } from '../group.service';

@Component({
  selector: 'app-group-edit',
  templateUrl: './group-edit.component.html',
  styleUrls: ['./group-edit.component.css']
})
export class GroupEditComponent implements OnInit {

  id: number = 0;
  group!: Group;

  constructor(private router: Router, private actRoute: ActivatedRoute, private groupService: GroupService) {

  }

  ngOnInit(): void {

    this.id=this.actRoute.snapshot.params['id'];

    this.groupService.getGroupById(this.id).subscribe(data=>{
      this.group=data;
    }, error=>console.log(error));

  }

  onSubmit(){
    this.groupService.updateGroup(this.id, this.group).subscribe(data=>{
      this.router.navigateByUrl("api/group-list");
    });

  }


/*
ngOnInit(): void {

  this.actRoute.params.subscribe(params => {

    this.id = params['id'];

    this.group=this.groupService.getGroupById(this.id);
  });
}
*/

}
