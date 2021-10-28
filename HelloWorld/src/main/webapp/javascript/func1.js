//func1.js
console.log('func1.js');

sum(10, 20); //호이스팅, function정의구문보다 먼저 나와도 실행 가능

function sum(num1,num2){
    let result = num1 + num2;
    console.log(result);
}

//함수가 변수에 담는 형식으로 선언할땐 호이스팅안됨!!
// let val = multi(2,5); 
// console.log(val);

let multi = function(n1,n2){
    let result = n1 * n2;
    return result;
}

let val = multi(2,5); //함수가 변수에 담는 형식으로 선언할땐 호이스팅안됨!!
console.log(val);