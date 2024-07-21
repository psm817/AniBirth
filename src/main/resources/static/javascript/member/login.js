const findPwButton = document.getElementById('findPw');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

findPwButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});