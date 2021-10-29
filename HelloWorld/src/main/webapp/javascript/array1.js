// array1.js
const persons = [];

function addFnc() {
    let addBtn = document.getElementById('add');
    let val = addBtn.value;
    console.log(val);

    persons.push(val); //배열의 마지막에 요소를 추가
    // persons.unshift(val); //배열의 첫번째에 추가
    console.log(persons);
}

function remFnc() {
    // persons.pop();
    // persons.shift(); //배열의 첫번째 삭제
    let findVal = document.getElementById('add').value;
    let cnt = -1;
    for (let i = 0; i < persons.length; i++) {
        if (persons[i] === findVal) {
            cnt = i;
            break;
        }
    }
    if (cnt >= 0) {
        persons.splice(cnt, 1);
    }
    console.log(persons);
}

document.write('<input type="text" id="add" value="hong">');
document.write('<button onclick="addFnc()">추가</button>');
document.write('<button onclick="remFnc()">삭제</button>');

const ary = ['hong', 'hwang']; //new Array(); 
console.log(ary[0]);
ary[0] = 'hong1'; //값 변경
ary[1] = 'hwang1';
ary[2] = 'park'; //값 추가
//3 값 안넣고 출력시 undefined
ary[ary.length] = 'kim';
ary[4] = 'choi';
ary[ary.length] = 'lee';
ary.push('new'); //제일 마지막에 추가
ary.push('new2');

delete ary[3];
ary.pop(); //젤 마지막위치에 있는 값을 지워줌

//대체값 안적으면 그냥 삭제
ary.splice(3, 2, 'new park', 'new kim', 'new lee'); //splice(인덱스위치, 지울갯수, 새로운값(대체값) 넣기) 
ary.splice(3, 0, 'new kim'); // 0이므로 삭제할 수 없고 그냥 추가됨
// ary.splice(3,1); // 3번째위치 1개 삭제

ary.unshift('first'); //제일 앞쪽에 추가
ary.shift(); //제일 앞쪽 삭제

for (let i = 0; i < ary.length; i++) {
    console.log(ary[i]);
}

console.log('----------');

for (let val of ary) {
    console.log(val);
}

const newAry = ary.slice(0,2); // slice(include:포함, exclude:포함x)
console.log(newAry);

const mergeAry = ary.concat(newAry); //배열 합침
console.log(mergeAry);

mergeAry.sort(); //정렬
mergeAry.reverse(); //역순 정렬
console.log(mergeAry);

const numbers = [1, 10, 100, 24, 4];
numbers.sort(function (f, l){  //메소드의 매개값으로 function 들어갈수있음, 콜백함수
    // if(f < l){
    //     return -1;
    // }else{
    //     return 1;
    // }
    return f - l; //음수 return : 오름차순 정렬
    // return l - f; //양수 return : 역순정렬
});
console.log(numbers);

const s1 = {
    name: 'hwang',
    score: 70
}
const s2 = {
    name: 'park',
    score: 80
}
const s3 = {
    name: 'choi',
    score: 90
}

const students = [s1, s2, s3];
students.sort(function(f,l){
    if(f.name < l.name){
        return -100;
    }else{
        return 1;
    }
});
console.log(students);



