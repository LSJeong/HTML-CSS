//array2.js

const numbers = [3, 34, 28, 12, 5];
let sum = 0;
numbers.forEach(function (val, ind, ary) { //각각의 요소들에 대한 처리 (val, index, ary)
    if (ind % 2 == 0) //1번째,3번째,5번째만 더하기(인덱스값은 0,2,4 이므로 %2 == 0)
        sum += val;

    // document.write(`<h1>${val}</h1>`);
});

console.log(`합계 : ${sum}`);



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

let str = '<h1>회원리스트</h1>';
str += '<table border="1">';
str += '<tbody>';
users.forEach(callBackFnc); //forEacn 매개값으로 callback 함수쓸때 알아서 실행해주므로 ()빼고 함수이름만 써줘야한다
str += '</tbody>';
str += '</table>';
document.write(str);


function callBackFnc(val, ind, ary){
    str += '<tr><td>' + val.id + '</td><td>' + val.name + '</td><td>' + val.point + '</td></tr>';
}



//버튼 클릭시 같은 페이지 내에 출력
document.write('<button onClick="createList()">생성</button>'); 
document.write('<div id="show"></div>');

function createList(){
    let fruits = ['Apple','Banana', 'Cherry'];
    let ulTag, liTag; //변수 선언
    ulTag = document.createElement('ul'); //<ul></ul>태그 생성

    for(let fruit of fruits){
        liTag = document.createElement('li'); //<li></li>태그 생성
        liTag.innerHTML = fruit;//'Apple'; // <li>Apple</li>
        liTag.id = fruit.toLowerCase(); //'apple';
        ulTag.appendChild(liTag); //<ul><li>Apple</li></ul>

    }

    // liTag = document.createElement('li');
    // liTag.innerHTML = 'Banana';
    // liTag.id = 'banana';
    // ulTag.appendChild(liTag);

    // liTag = document.createElement('li');
    // liTag.innerHTML = 'Cherry';
    // liTag.id = 'cherry';
    // ulTag.appendChild(liTag);

    document.getElementById("show").appendChild(ulTag);
    console.log(ulTag);
}
