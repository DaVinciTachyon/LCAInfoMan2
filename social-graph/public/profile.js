// We can get the token from the "access_token" query
// param, available in the browsers "location" global
const query = window.location.search.substring(1);
const token = query.split('access_token=')[1];

// Call the user info API using the fetch browser library
fetch('//api.github.com/user', {
	headers: {
		// Include the token in the Authorization header
		Authorization: 'token ' + token
	}
})
	// Parse the response as JSON
	.then((res) => res.json())
	.then((res) => {
		// Once we get the response (which has many fields)
		// Documented here: https://developer.github.com/v3/users/#get-the-authenticated-user
		var imgNode = document.createElement('img');
		imgNode.src = res.avatar_url;
		imgNode.alt = `${res.name}`;
		document.getElementById('profile').appendChild(imgNode);
		var nameNode = document.createElement('div');
		nameNode.className = 'container';
		nameNode.textContent = `${res.name}`;
		document.getElementById('profile').appendChild(nameNode);
	});
