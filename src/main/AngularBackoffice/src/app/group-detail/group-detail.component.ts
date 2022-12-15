import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Group } from '../group';
import { GroupService } from '../group.service';

@Component({
  selector: 'app-group-detail',
  templateUrl: './group-detail.component.html',
  styleUrls: ['./group-detail.component.css']
})
export class GroupDetailComponent implements OnInit{

  id!:number;
  group!:Group;

  constructor(private actRoute:ActivatedRoute, private router:Router, private groupService:GroupService){

  }

ngOnInit(): void {
  this.id=this.actRoute.snapshot.params['id'];
  
  this.groupService.getGroupById(this.id).subscribe(data=>{
    this.group=data;
  });


}


}
