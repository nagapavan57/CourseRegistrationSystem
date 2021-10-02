import { User } from "./user";


export class Student extends User {

    
    public studentId:number;
    public name:String;
    public emailId:String;
    public branchName:String;
    public Address:String;

    constructor(userId: string, password: String,name:String,emailId:String,
        branchName:String,Address:String,studentId:number) {
        super(userId,password);
        this.name=name;
        this.emailId=emailId;
        this.branchName=branchName;
        this.Address=Address;
        this.studentId=studentId;
    }

}
