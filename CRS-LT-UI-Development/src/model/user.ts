export class User {

    public userId: string;
    public password: String;

    constructor(name: string, password: String) {
        this.userId = name;
        this.password = password;
    }
}
