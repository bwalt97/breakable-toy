import React, {Component} from 'react'
import RandomMovieService from "../service/RandomMovieService";
import {makeStyles, Theme, createStyles} from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import ThumbUpIcon from '@material-ui/icons/ThumbUp';
import ThumbDownIcon from '@material-ui/icons/ThumbDown';
import Card from '@material-ui/core/Card';
import {CardActionArea} from "@material-ui/core";
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import IconButton from '@material-ui/core/IconButton';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import clsx from 'clsx';
import Collapse from '@material-ui/core/Collapse';

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        root: {
            maxWidth: 1000,
            margin: 'auto'
        },
        media: {
            height: 444,
            width: 300,
            justifyContent: 'center',
            alignContent: 'center',
        },
        expand: {
            transform: 'rotate(0deg)',
            marginLeft: 'auto',
            transition: theme.transitions.create('transform', {
                duration: theme.transitions.duration.shortest,
            }),
        },
        expandOpen: {
            transform: 'rotate(180deg)',
        },
    }));

class RandomMovieComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            id: null,
            title: null,
            overview: null,
            poster_path: null,
            expanded: false
        }
    }

    componentDidMount() {

        RandomMovieService.getRandomMovie().then((response) => {
            this.setState(
                {
                    id: response.data.id,
                    title: response.data.title,
                    overview: response.data.overview,
                    poster_path: response.data.poster_path
                })
        });

    }

    likeMovie() {

        RandomMovieService.likeMovie(this.state.id).then(this.componentDidMount.bind(this));

    }


    render() {
        const classes = this.props.classes;

        return (

            <div style={{
                backgroundImage: `url(${"https://image.tmdb.org/t/p/w500/" + this.state.poster_path})`,
                backgroundPosition: 'center',
                backgroundRepeat: 'no-repeat',
                backgroundSize: 'cover'
            }}>
                <div style={{
                    background: 'rgba(165,165,165,0.8)'
                }}>
                    <Grid
                        container
                        spacing={0}
                        direction="column"
                        alignItems="center"
                        justify="center"
                        style={{minHeight: '100vh'}}
                    >
                        <Grid item xs={2}>
                            <Card className={classes.root}>
                                <CardActionArea>
                                    <CardMedia
                                        className={classes.media}
                                        image={"https://image.tmdb.org/t/p/w500/" + this.state.poster_path}
                                    />
                                    <CardContent>
                                        <Typography gutterBottom variant="h5" component="h2">
                                            {this.state.title}
                                        </Typography>
                                    </CardContent>
                                </CardActionArea>
                                <CardActions>
                                    <Button
                                        startIcon={<ThumbDownIcon/>}
                                        size={"large"}
                                        variant={"contained"}
                                        color={"primary"}
                                        onClick={this.componentDidMount.bind(this)}>
                                        Skip
                                    </Button>
                                    <Button
                                        endIcon={<ThumbUpIcon/>}
                                        size={"large"}
                                        variant={"contained"}
                                        color={"secondary"}
                                        onClick={this.likeMovie.bind(this)}>
                                        Interested
                                    </Button>
                                    <IconButton
                                        className={clsx(classes.expand, {
                                            [classes.expandOpen]: this.state.expanded,
                                        })}
                                        onClick={() => this.setState({expanded: !this.state.expanded})}
                                        aria-expanded={this.state.expanded}
                                        aria-label="show more"
                                    >
                                        <ExpandMoreIcon/>
                                    </IconButton>
                                </CardActions>
                                <Collapse in={this.state.expanded} timeout="auto" unmountOnExit>
                                    <CardContent>
                                        <Typography paragraph>
                                            Description:
                                        </Typography>
                                        <Typography paragraph>
                                            {this.state.overview}
                                        </Typography>
                                    </CardContent>
                                </Collapse>
                            </Card>
                        </Grid>
                    </Grid>
                </div>
            </div>
        )
    }

}

export default () => {
    const classes = useStyles();

    return (
        <RandomMovieComponent classes={classes}/>
    )
}

