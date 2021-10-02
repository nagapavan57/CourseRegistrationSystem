export class Course {

    public courseCode:String;
    public courseName:String;
    public seats:String;
    public fee:String;

    constructor(courseCode:String,courseName:String,seats:String,fee:String){
            this.courseCode=courseCode;
            this.courseName=courseName;
            this.seats=seats;
            this.fee=fee;
    }
}
