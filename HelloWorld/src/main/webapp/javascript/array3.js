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

let resultAry = [];
let sum = filterUsers.reduce(function(prevObj,curObj,ind,ary){  //prev:이전값, cur:현재값
    console.log(prevObj,curObj.score);
    return (prevObj + curObj.score);
},0); //0: 초기값,  prev에 cur값을 더해서 누적시킴
//초기값이 없으면 prev 요소를 초기값으로 잡음
console.log(`합계 : ${sum}`);

