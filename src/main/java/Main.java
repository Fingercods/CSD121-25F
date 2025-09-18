void CheckAge(int age ){
    if (age >= 18){
        IO.println("You are an adult");
    }
    else{
        IO.println("You are a minor");
    }
}

void main(){
    var UserAge = IO.readln("Please enter your age:");

    int age = Integer.parseInt(UserAge);

    CheckAge(age);
}