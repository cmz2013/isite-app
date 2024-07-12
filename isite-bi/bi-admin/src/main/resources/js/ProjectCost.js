function startWith(data,match) {
    return data.indexOf(match)==0;
}
function endWith(data,match) {
    var d=data.length-match.length;
    return (d>=0&&data.lastIndexOf(match)==d);
}
function contains(data,match) {
    return data.indexOf(match)>=0;
}
function equals(data,match) {
    return data==match;
}