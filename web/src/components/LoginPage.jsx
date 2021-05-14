import '../LoginPage.css';
import { useState } from 'react';
import { createSession } from '../services';
import { errorMessages } from '../error-messages';
import ErrorMessage from './ErrorMessage';


const Login = function ({ onLogin, onBack }) {

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isDisabled, setIsDisabled] = useState(true);
  const [isPending, setIsPending] = useState(false);
  const [status, setStatus] = useState('');

  const onUserInput = (e) => {
    setStatus('');
    setUsername(e.target.value);
    setIsDisabled(!e.target.value);
  };

  const onPasswordInput = (e) => {
    setStatus('');
    setPassword(e.target.value)
  }

  const login = () => {
    setIsPending(true);
    createSession(username, password)
      .then(data => {

        setStatus('');
        setUsername("");
        setPassword("")
        setIsPending(false);
        onLogin(data);
      })
      .catch(err => {
        setStatus(errorMessages[err.error]);
        setIsPending(false);
      });
  };

  return (
    <div className='loginPage'>
      <h1>Welcome!</h1>
      <div className='login-form'>
        <form action="">
          <input disabled={isPending} onChange={onUserInput} value={username} placeholder='Username' />
          <input disabled={isPending} onChange={onPasswordInput} value={password} placeholder='Password' type='password' />
        </form>
        <button className='login-button' onClick={login} disabled={isDisabled || isPending} > {isPending ? "..." : "Login"}</button>
        <p>
          <input className='button' type="button" value='Back' onClick={onBack} />
        </p>
        {status && <ErrorMessage onChange={status} />}
      </div>
    </div>
  );
};

export default Login;
