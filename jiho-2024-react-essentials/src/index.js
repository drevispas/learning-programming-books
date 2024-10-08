import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import TodoApp from "./hooks/useState/TodoApp";
import CounterApp from "./hooks/useState/CounterApp";
import UserFetcherApp from "./hooks/useEffect/UserFetcherApp";
import InputFocusApp from "./hooks/useRef/InputFocusApp";
import ExpensiveCalculationApp from "./hooks/useMemo/ExpensiveCalculationApp";
import ReducerCounterApp from "./hooks/useReducer/ReducerCounterApp";
import ThemeContextApp from "./context/ThemeContextApp";
import ReduxCounterApp from "./redux/ReduxCounterApp";
import {RouterApp} from "./routes/router/RouterApp";
import {RestrictedRouteApp} from "./routes/protectedRoute/RestrictedRouteApp";
import {ControlledFormApp} from "./forms/ControlledFormApp";
import {UncontrolledFormApp} from "./forms/UncontrolledFormApp";
import {FormValidationApp} from "./forms/FormValidationApp";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        {/*<TodoApp/>*/}
        {/*<hr/>*/}
        {/*<CounterApp/>*/}
        {/*<hr/>*/}
        {/*<UserFetcherApp/>*/}
        {/*<hr/>*/}
        {/*<InputFocusApp/>*/}
        {/*<hr/>*/}
        {/*<ExpensiveCalculationApp/>*/}
        {/*<hr/>*/}
        {/*<ReducerCounterApp/>*/}
        {/*<hr/>*/}
        {/*<ThemeContextApp/>*/}
        {/*<hr/>*/}
        {/*<ReduxCounterApp/>*/}
        {/*<hr/>*/}
        {/*<RouterApp/>*/}
        {/*<hr/>*/}
        {/*<RestrictedRouteApp/>*/}
        {/*<hr/>*/}
        <ControlledFormApp/>
        <hr/>
        <UncontrolledFormApp/>
        <hr/>
        <FormValidationApp/>
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
