import {useState} from 'react';
import Map from './components/map/Map.jsx';

import './App.css'

function App() {
    const [count, setCount] = useState(0)

    return (
        <>
            <h1>Map</h1>
            <Map />
        </>

    )
}

export default App
