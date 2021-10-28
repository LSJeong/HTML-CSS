// func2.js
let str = '<h1>Hello</h1>';
str += '<ul><li>Apple</li><li>Banana</li><li>Cherry</li></ul>';

document.write(str);

let li = document.querySelector('ul>li');
console.log(li);
li.style.color = 'red';
li.style.backgroundColor = 'yellow';
// li.remove();


console.log(this); //object => {key: value, key1: value1}, this는 window object라고 생각하면됨
let obj = {
    key: 'value',
    key1: 'value1',
    key2: {
        key3: 'value3',
        key4: 'value4',
        key5: {
            key6: 'value6',
            key7: {
                key8: 'value8'
            }
        }
    }
}

obj.key2.key3;  // => value3 읽어옴
console.log(obj.key2.key5.key6); // => value6
console.log(obj.key2.key5['key6']);
console.log(obj.key2['key5']['key6']);
console.log(this.document.body);