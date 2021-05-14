import './App.css';
import React, { useState, useEffect } from 'react';
import Shortener from './components/Shortern';
import Retrieve from './components/Retrieve';
import ShowAllUrlPage from './components/ShowAllUrlPage';
import LoginPage from './components/LoginPage';
import Header from './components/Header';
import Login from './components/Login';
import Logout from './components/Logout';
import ShowAll from './components/ShowAll';
import { errorMessages } from './error-messages';
import ErrorMessage from './components/ErrorMessage';
import { endSession, checkSession, dump } from './services';

function App() {
  const [userState, setUserState] = useState({ isLoggedIn: false, isPending: true });
  const [showDetail, setShowDetail] = useState(false);
  const [list, setList] = useState();
  const [showLogin, setShowLogin] = useState(false);
  const [status, setStatus] = useState('');


  useEffect(() => {
    checkSession()
      .then(() => {
        setUserState({
          isLoggedIn: true,
          isPending: false,
        });
      })
      .catch(err => {
        setStatus(errorMessages[err.error]);
        setUserState({
          isLoggedIn: false,
          isPending: false,
        });
      })
  }, []);

  const onLogin = function () {
    setUserState({
      isLoggedIn: true,
      isPending: false,
    });
    setShowLogin(!showLogin);
  };

  const onBack = function() {
    setUserState({
      isLoggedIn: false,
      isPending: false,
    });
    setShowLogin(false);
  }

  const show = function () {
    setShowDetail(!showDetail)
  };

  const onShowLog = function () {
    setShowLogin(!showLogin);
  }


  const onLogout = function () {
    setUserState({
      isLoggedIn: false,
      isPending: false,
    });

    setUserState({
      ...userState,
      isPending: true,
    });

    endSession()
      .then(() => {
        setUserState({
          isLoggedIn: false,
          isPending: false,
        });
      })
      .catch(() => {
        setUserState({
          ...userState,
          isPending: false,
        });
      });
  };

  function handleClick() {
    dump()
      .then(value => {
        setList(value);
        show();
      })
      .catch(err => {
        setStatus(errorMessages[err.error]);
        console.log(err);
      })
  }

  if (userState.isPending) {
    return <div></div>;
  }

  return (
    <div className="App">

      {!showLogin && !showDetail &&
        <div className='container'>
          <Header />
          {!userState.isLoggedIn && <Login onLogin={onShowLog} />}
          {userState.isLoggedIn && <Logout onLogout={onLogout} />}
          {userState.isLoggedIn && <ShowAll onShow={handleClick} >Show All</ShowAll>}
          <Shortener />
          <br />
          <Retrieve />
        </div>
      }
      {status && <ErrorMessage onChange={status} />}
      {showLogin && <LoginPage onLogin={onLogin} onBack={onBack}/>}
      {showDetail && userState.isLoggedIn && <ShowAllUrlPage onShowDetail={show} content={list} />}
    </div>
  );
}

export default App;
