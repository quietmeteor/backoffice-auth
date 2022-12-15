import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Group } from '../group';
import { GroupService } from '../group.service';

@Component({
  selector: 'app-group-create',
  templateUrl: './group-create.component.html',
  styleUrls: ['./group-create.component.css']
})
export class GroupCreateComponent implements OnInit {

  group!: Group;


  constructor(private router: Router, private groupService: GroupService) {

  }

  ngOnInit(): void {

  }

  saveGroup() {
    this.groupService.createGroup(this.group).subscribe(data => {
      console.log(data);
      this.goToGroupList();
    })
  }

  goToGroupList() {
    this.router.navigate(['api/group-list']);
  }

  onSubmit() {
    console.log(this.group);
    this.saveGroup();
  }

}
