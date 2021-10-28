// func3.js
function multiply(num){
    // 3단
    document.write('<table border="1" style="text-align: center;"><tbody>');
    for(let i=1;i <=9;i++){
        // document.write('<p>'+ num +' * ' + i + ' = ' + (num*i) + '</p>');
        // document.write('<tr><td>'+ num +' * ' + i + ' = ' + (num*i) + '</td></tr>');
        document.write('<tr>');
        document.write('<td>' + num + '</td>');
        document.write('<td> * </td>');
        document.write('<td>' + i + '</td>');
        document.write('<td> = </td>');
        document.write('<td style="background-color: cadetblue;">' + (num*i) + '</td>');
        document.write('</tr>');
    }
    document.write('</tbody></table>');
}


function multiplyWithReturn(num){
    let str = '';
    str = '<table border="1" style="text-align: center;"><tbody>';
    for(let i=1;i <=9;i++){
        str += '<tr>';
        str += '<td>' + num + '</td>';
        str += '<td> * </td>';
        str += '<td>' + i + '</td>';
        str += '<td> = </td>';
        str += '<td style="background-color: yellow;">' + (num*i) + '</td>';
        str += '</tr>';
    }
    str += '</tbody></table>';

    return str;
}

multiply(3);

document.write('<br>'+multiplyWithReturn(7));