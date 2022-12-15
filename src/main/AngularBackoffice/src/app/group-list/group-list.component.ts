import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Group } from '../group';
import { GroupService } from '../group.service';
@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['./group-list.component.css']
})
export class GroupListComponent implements OnInit {

  groupList: Array<Group> = new Array();

  constructor(private groupService: GroupService, private actRoute: ActivatedRoute, private router: Router) {

  }

  ngOnInit(): void {

    this.groupService.getAllGroups().subscribe((response => {
      console.log(response);

      let responseList = (response as Array<Group>);

      responseList.forEach((g) => {

        console.log(g.id);
        console.log(g.groupName);

        this.groupList.push({
          id: g.id,
          groupName:  g.groupName,
          permissions: g.permissions,
          enabled: g.enabled,
          creationUser: g.creationUser,
          creationTime: new Date(g.creationTime),
          updateUser: g.updateUser,
          updateTime: new Date(g.updateTime),
          roles: g.roles

        });

      });

    }));

  }


  createGroup() {
    this.router.navigateByUrl("api/group-create");
  }


  detail(id: number) {
    this.router.navigateByUrl("api/group-detail/" + id);
  }

  edit(id: number) {
    this.router.navigateByUrl("api/group-edit/" + id);

  }

  deleteGroup(id:number) {
    if(confirm("Sei sicuro di voler eliminare il gruppo?")){
      this.groupService.deleteGroupById(id);
      this.ngOnInit();
    }

  }

}
