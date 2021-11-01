// array3.js(Array.map, Array.filter)

function User(id, name, point) { //생성자 함수
    this.id = id;
    this.name = name;
    this.point = point;
}

const users = [new User('user1', '사용자1', 90),
    new User('user2', '사용자2', 110),
    new User('user3', '사용자3', 200),
    new User('user4', '사용자4', 30)
];

console.log(users);

const newUsers = users.map(function (val, ind, ary){
    let newObj = {}  //object 선언
    newObj.uid = val.id;
    newObj.uname = val.name;
    newObj.score = val.point;
    newObj.idx = ind;

    return newObj;
});
console.log(newUsers);

const filterUsers = newUsers.filter(function(val,ind){  //filter: 참값만 return
    // return val.idx > 1; //idx가 1보다 큰것만 리턴
    // return true; //전체 다 리턴
    return val.idx //0, null은 false니까 빼고 리턴
}); 
console.clear();
console.log(filterUsers);


let sum = filterUsers.reduce(function(initVal,obj,ind,ary){  
    console.log(initVal, obj, ind, ary);
    initVal.push(obj);
    return initVal;
    // return initVal + obj.score;
},[]); //  초기값지정(0, {} obj, [] 배열 도 올수있다)
//참조값... a=3, a={}
console.log(sum);

let sum2 = function sum2(num1, num2){
    return num1 + num2;
}
//arrow function
sum2 = (num1, num2) => num1 + num2; //간단 표현

let trueOrFalse = [45, 4, 9, 16, 25].every(function(val, ind, ary){   //.some 하나라도 만족하는게 있는지
    return val > 10;  //모든 요소들이 만족하면 true, 아니면 false
});
console.log(trueOrFalse);

const fruits = ['Apple','Orange','Apple','Mango'];
console.log(fruits.indexOf('Apple'));
console.log(fruits.lastIndexOf('Apple',1)); 
console.log(fruits.includes('Apple')); //배열안에 이 값이 있는가? ture or false
console.log(fruits.findIndex(function(val,ind,ary){
    return val == 'Apple';
})); 

//문자열을 배열로 반환
console.log(Array.from('ABCD'));
console.log('A,B,C,D'.split(","));

// console.log(fruits.keys());
for(let x of fruits.keys()){
    console.log(x);
}

// object에서는 keys() 못씀.. 배열은 가능..
// let user1 = new User('user1','사용자1', 90);
// console.log(users1.keys());
// for(let x of user1.keys()){
//     console.log(x);
// }


