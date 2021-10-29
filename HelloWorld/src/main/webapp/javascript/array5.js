// array5.js
// 요소(태그)생성: document.createElement('태그이름')
// 상위요소 > 하위요소 : 상위요소.appendChile(하위요소)

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

//표 형식(table)으로 생성
document.write('<button onclick="createContent()">생성</button>');
document.write('<div id="show"></div>');

function createContent() {
    let tTag,thTag, th2Tag ,bTag, rTag, dTag;
    tTag = document.createElement('table');
    tTag.setAttribute('border',1);
    tTag.style.borderBlockColor = "red";

    thTag = document.createElement('thead');
    tTag.appendChild(thTag);

    for(let field in users[0]){
        th2Tag = document.createElement('th');
        th2Tag.innerHTML = field;
        thTag.appendChild(th2Tag);
    }

    bTag = document.createElement('tbody');
    tTag.appendChild(bTag);

    for (let i = 0; i < users.length; i++) {
        rTag = document.createElement('tr');
        bTag.appendChild(rTag);
        for (let field in users[i]) {
            dTag = document.createElement('td');
            dTag.innerHTML = users[i][field];
            rTag.appendChild(dTag);
        }
    }


    document.getElementById("show").appendChild(tTag);
    console.log(tTag);
}