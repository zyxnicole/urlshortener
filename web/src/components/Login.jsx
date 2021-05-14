import inLogo from '../img/inLogo.png';

const Login = function ({ onLogin }) {
  return (
    <div id='login' >
      <div class="navigation">
        <div class="button">
          <img src={inLogo} alt='login' />
          <div className="login" onClick={onLogin} >LOGIN </div>
        </div>
      </div>
    </div>
  )
};

export default Login;
