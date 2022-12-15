export interface User{
    id:number;
    username:string;
    password:string;
    email:string;
    name:string;
    lastName:string;
    groupName:string;
    groupId:number;
    lastLogin:Date;
    dateModifiedPass:Date;
    verified:boolean;
    deleted:boolean;

}