import inLogo from '../img/inLogo.png';

const Login = function ({ onLogin }) {
  return (
    <div id='login' >
      <div className="navigation">
        <div className="button">
          <img src={inLogo} alt='login' />
          <div className="login" onClick={onLogin} >LOGIN </div>
        </div>
      </div>
    </div>
  )
};

export default Login;
