/**
 * 
 */
function goPage(url)
{
	location.href=url;
}

function ajax(conf) {
	var xhr = new XMLHttpRequest();
	xhr.open(conf.method, conf.url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				conf.callback(xhr.responseText);
			}
		}
	}
	xhr.send();
}