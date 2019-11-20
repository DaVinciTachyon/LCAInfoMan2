function punchCardGraph(user, repo, graphID) {
	// set the dimensions and margins of the graph
	var margin = { top: 60, right: 60, bottom: 60, left: 60 },
		width = 580 - margin.left - margin.right,
		height = 500 - margin.top - margin.bottom;

	// append the svg object to the body of the page
	var svg = d3
		.select('#' + graphID)
		.append('svg')
		.attr('width', width + margin.left + margin.right)
		.attr('height', height + margin.top + margin.bottom)
		.append('g')
		.attr('transform', 'translate(' + margin.left + ',' + margin.top + ')');

	//Read the data
	d3.json('//api.github.com/repos/' + user + '/' + repo + '/stats/punch_card', function(data) {
		// ---------------------------//
		//       AXIS  AND SCALE      //
		// ---------------------------//

		// Add X axis
		var x = d3
			.scaleLinear()
			.domain([
				0,
				6
			])
			.range([
				0,
				width
			]);
		svg.append('g').attr('transform', 'translate(0,' + height + ')').call(d3.axisBottom(x).ticks(7));

		// Add X axis label:
		svg.append('text').attr('text-anchor', 'end').attr('x', width).attr('y', height + 50).text('Day');

		// Add Y axis
		var y = d3
			.scaleLinear()
			.domain([
				0,
				23
			])
			.range([
				height,
				0
			]);
		svg.append('g').call(d3.axisLeft(y).ticks(24));

		// Add Y axis label:
		svg
			.append('text')
			.attr('text-anchor', 'end')
			.attr('x', 0)
			.attr('y', -20)
			.text('Hour')
			.attr('text-anchor', 'start');

		// Add a scale for bubble size
		var z = d3
			.scaleSqrt()
			.domain([
				0,
				25
			])
			.range([
				0,
				20
			]);

		// Add a scale for bubble color
		var myColor = d3
			.scaleSqrt()
			.domain([
				0,
				25
			])
			.range([
				'white',
				'green'
			]);

		// ---------------------------//
		//      TOOLTIP               //
		// ---------------------------//

		// -1- Create a tooltip div that is hidden by default:
		var tooltip = d3
			.select('#' + graphID)
			.append('div')
			.style('opacity', 0)
			.attr('class', 'tooltip')
			.style('background-color', 'black')
			.style('border-radius', '5px')
			.style('padding', '10px')
			.style('color', 'white')
			.style('position', 'absolute');

		// -2- Create 3 functions to show (when mouse move but stay on same circle) / hide the tooltip
		var showTooltip = function(d) {
			tooltip.transition().duration(200);
			tooltip
				.style('opacity', 1)
				.html('Commits: ' + d[2])
				.style('left', d3.select(this).attr('cx') + 'px')
				.style('top', d3.select(this).attr('cy') + 'px');
		};

		var hideTooltip = function(d) {
			tooltip.transition().duration(200).style('opacity', 0);
		};

		// ---------------------------//
		//       HIGHLIGHT GROUP      //
		// ---------------------------//

		// What to do when one group is hovered
		var highlight = function(d) {
			// reduce opacity of all groups
			d3.selectAll('.bubbles').style('opacity', 0.05);
			// expect the one that is hovered
			d3.selectAll('.' + d).style('opacity', 1);
		};

		// And when it is not hovered anymore
		var noHighlight = function(d) {
			d3.selectAll('.bubbles').style('opacity', 1);
		};

		// ---------------------------//
		//       CIRCLES              //
		// ---------------------------//

		// Add dots
		svg
			.append('g')
			.selectAll('dot')
			.data(data)
			.enter()
			.append('circle')
			.attr('class', function(d) {
				return 'bubbles ' + d[2];
			})
			.attr('cx', function(d) {
				return x(d[0]);
			})
			.attr('cy', function(d) {
				return y(d[1]);
			})
			.attr('r', function(d) {
				return z(d[2]);
			})
			.style('fill', function(d) {
				return myColor(d[2]);
			})
			// -3- Trigger the functions for hover
			.on('mouseover', showTooltip)
			.on('mouseleave', hideTooltip);

		// ---------------------------//
		//       TITLE                //
		// ---------------------------//

		// Add title to graph
		var titleNode = document.createElement('div');
		titleNode.id = graphID + '_title';
		titleNode.textContent = repo;
		titleNode.style = 'font-size: 22px; padding: 5px; margin: 2px;';
		document.getElementById(graphID).prepend(titleNode);

		// Add subtitle to graph
		var subTitleNode = document.createElement('div');
		subTitleNode.id = graphID + '_subTitle';
		subTitleNode.textContent = user;
		subTitleNode.style = 'font-size: 14px; color: grey; padding: 2px; margin: 2px;';
		document.getElementById(graphID + '_title').append(subTitleNode);
	});
}
