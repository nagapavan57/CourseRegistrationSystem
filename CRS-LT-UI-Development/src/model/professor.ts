import { User } from "./user";

export class Professor extends User {

    public name:String;
    public subject:String;
    public department:String;
    public profId:string
    

    constructor(userId: string, password: String,name:String,subject:String,
        department:String) {
        super(userId,password);
        this.name=name;
        this.subject=subject;
        this.department=department;
    }
}
